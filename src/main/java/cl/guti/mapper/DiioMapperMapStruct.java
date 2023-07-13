package cl.guti.mapper;

import org.mapstruct.Mapper;

import cl.guti.DTO.DiioDto;
import cl.guti.model.Diio;

@Mapper(componentModel = "spring")
public interface DiioMapperMapStruct {

	Diio DiioDtoToDiio(DiioDto diioDto);
	DiioDto DiioToDiioDto(Diio diio);
}
