package com.secdn.secdnrapid.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.google.common.reflect.TypeToken;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * 例 TestConvert;SOURCE:TestConvertEntity;TARGET:TestConvertDto
 *
 * <p>1、SOURCE 与 TARGET 中属性名相同的默认映射(如这两个都有address属性)
 *
 * <p>2、SOURCE 与TARGET中属性名不同的，需要通过 @Mappings({@Mapping()}) 明确关系来形成映射(如userName对应name)
 *
 * <p>3、形成映射关系的属性类型不同的,需要通过表达式转换数据类型类型(如Date对应String)
 *
 * <p>4、无映射关系属性被忽略(如TestConvertEntity的password)
 *
 * @author secdn
 * @create 2019-08-27
 */
public interface BaseConvert<SOURCE, TARGET> {

  /** 映射同名属性 */
  TARGET entityToDto(SOURCE source);

  /** 反向，映射同名属性 */
  @InheritInverseConfiguration(name = "entityToDto")
  SOURCE dtoToEntity(TARGET target);

  /** 映射同名属性，集合形式 */
  @InheritConfiguration(name = "entityToDto")
  List<TARGET> entityToDto(List<SOURCE> sourceList);

  /** 反向，映射同名属性，集合形式 */
  @InheritConfiguration(name = "dtoToEntity")
  List<SOURCE> dtoToEntity(List<TARGET> targetList);

  /** 更新属性 */
  @InheritConfiguration(name = "entityToDto")
  void updateEntityToDto(SOURCE source, @MappingTarget TARGET target);

  /** 反向，更新属性 */
  @InheritConfiguration(name = "dtoToEntity")
  void updateDtoToEntity(@MappingTarget SOURCE source, TARGET target);

  /** 映射同名属性，集合流形式 */
  List<TARGET> entityToDto(Stream<SOURCE> sourceStream);

  /** 反向，映射同名属性，集合流形式 */
  List<SOURCE> dtoToEntity(Stream<TARGET> targetStream);

  default SOURCE mapToEntity(Map<String, Object> map) {
    try {
      TypeToken<SOURCE> dtoType = new TypeToken<SOURCE>(getClass()) {};
      Class<SOURCE> rawType = (Class<SOURCE>) dtoType.getRawType();
      String s = JSONObject.toJSONString(map);
      return JSONObject.parseObject(s, rawType);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  default Map<String, Object> entityToMap(SOURCE source) {
    String s = JSONObject.toJSONString(source);
    return JSONObject.parseObject(s, Map.class);
  }

  default List<Map<String, Object>> listEntityToListMap(List<SOURCE> sourceList) {
    List<Map<String, Object>> listMap = new ArrayList<>();
    for (SOURCE source : sourceList) {
      listMap.add(entityToMap(source));
    }
    return listMap;
  }

  default List<SOURCE> listMapToListEntity(List<Map<String, Object>> listMap) {
    String s = JSONObject.toJSONString(listMap);
    try {
      TypeToken<SOURCE> dtoType = new TypeToken<SOURCE>(getClass()) {};
      Class<SOURCE> rawType = (Class<SOURCE>) dtoType.getRawType();
      return JSONObject.parseArray(s, rawType);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  default TARGET mapToDto(Map<String, Object> map) {
    try {
      TypeToken<TARGET> dtoType = new TypeToken<TARGET>(getClass()) {};
      Class<TARGET> rawType = (Class<TARGET>) dtoType.getRawType();
      String s = JSONObject.toJSONString(map);
      return JSONObject.parseObject(s, rawType);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  default Map<String, Object> dtoToMap(TARGET target) {
    String s = JSONObject.toJSONString(target);
    return JSONObject.parseObject(s, Map.class);
  }

  default List<Map<String, Object>> listDtoToListMap(List<TARGET> targetList) {
    List<Map<String, Object>> listMap = new ArrayList<>();
    for (TARGET source : targetList) {
      listMap.add(dtoToMap(source));
    }
    return listMap;
  }

  default List<TARGET> listMapToListDto(List<Map<String, Object>> listMap) {
    String s = JSONObject.toJSONString(listMap);
    try {
      TypeToken<TARGET> dtoType = new TypeToken<TARGET>(getClass()) {};
      Class<TARGET> rawType = (Class<TARGET>) dtoType.getRawType();
      return JSONObject.parseArray(s, rawType);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
