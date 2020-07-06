package com.demo.modelmapper;

import java.io.Serializable;
import java.util.HashMap;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CustomAttributes extends HashMap<String, Object> implements Serializable {

  private static final long serialVersionUID = -17597648216283414L;
}