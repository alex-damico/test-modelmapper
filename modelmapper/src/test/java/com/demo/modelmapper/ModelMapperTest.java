package com.demo.modelmapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class ModelMapperTest {

  private ModelMapper modelMapper;
  private ObjectMapper objectMapper;

  @BeforeEach
  void init() {
    this.modelMapper = new ModelMapper();
    this.modelMapper.getConfiguration().setAmbiguityIgnored(true);
    this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

    this.objectMapper = new ObjectMapper();
    this.objectMapper.registerModule(new Jdk8Module());
  }

  @Test
  void shouldCopyCustomAttributes() {
    CustomAttributesDTO customAttributesDTO = new CustomAttributesDTO();
    customAttributesDTO.put("test", "test");
    customAttributesDTO.put("1", "2");

    CustomAttributes customAttributes = this.modelMapper
        .map(customAttributesDTO, CustomAttributes.class);

    CustomAttributes expected = new CustomAttributes();
    expected.put("test", "test");
    expected.put("1", "2");

    assertEquals(expected, customAttributes);
  }

  @Test
  void shouldCopyBookWithoutCustomAttributes() {
    BookDTO bookDTO = BookDTO.builder().name("name").build();
    Book book = this.modelMapper.map(bookDTO, Book.class);
    Book expected = Book.builder().name("name").build();
    assertEquals(expected, book);
  }

  @Test
  void shouldCopyBookWithCustomAttributes() {
    CustomAttributesDTO customAttributesDTO = new CustomAttributesDTO();
    customAttributesDTO.put("test", "test");
    customAttributesDTO.put("1", "2");

    BookDTO bookDTO = BookDTO.builder().name("name").customAttributes(customAttributesDTO).build();
    Book book = this.modelMapper.map(bookDTO, Book.class);

    CustomAttributes customAttributes = new CustomAttributes();
    customAttributes.put("test", "test");
    customAttributes.put("1", "2");

    Book expected = Book.builder().name("name").customAttributes(customAttributes).build();
    assertEquals(expected, book);
  }

}
