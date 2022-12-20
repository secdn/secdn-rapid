package com.secdn.secdnrapid.modules.business.convert;

import com.secdn.secdnrapid.modules.business.dto.TestConvertDto;
import com.secdn.secdnrapid.modules.business.entity.TestConvertEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

/**
 * @author secdn
 * @create 2019-08-28
 */
@Mapper
public interface TestConvert {
  TestConvert INSTANCE = Mappers.getMapper(TestConvert.class);


  @Mappings({})
  TestConvertDto entityToDto(TestConvertEntity testConvertEntity);

  @Mappings({})
  TestConvertEntity dtoToEntity(TestConvertDto testConvertDto);
}
