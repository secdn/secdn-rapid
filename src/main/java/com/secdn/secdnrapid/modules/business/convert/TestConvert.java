package com.secdn.secdnrapid.modules.business.convert;

import com.secdn.secdnrapid.common.utils.BaseConvert;
import com.secdn.secdnrapid.modules.business.dto.TestConvertDto;
import com.secdn.secdnrapid.modules.business.entity.TestConvertEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author secdn
 * @create 2019-08-28
 */
@Mapper(componentModel = "spring")
public interface TestConvert extends BaseConvert<TestConvertEntity, TestConvertDto> {

  @Override
  @Mappings({@Mapping(source = "userName", target = "name")})
  TestConvertDto entityToDto(TestConvertEntity testConvertEntity);

  @Override
  @Mappings({
    @Mapping(source = "name", target = "userName"),
    @Mapping(target = "password", ignore = true)
  })
  TestConvertEntity dtoToEntity(TestConvertDto testConvertDto);
}
