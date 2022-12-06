package com.newjeans.quickboard.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@AllArgsConstructor
@Getter
public class SliceResDto<D> {
    private int numberOfElements;
    private boolean hasNext;
    private List<D> dto;


    /*public static <E, D> SliceResDto create(Slice<E> entity, Function<E, D> makeDto) {
        List<D> dto = convertToDto(entity, makeDto);
        return new SliceResDto(entity.getNumberOfElements(), entity.hasNext(), dto);
    }

    private static <E, D> List<D> convertToDto(Slice<E> entity, Function<E, D> makeDto) {
        return entity.getContent().stream()
                .map(e -> makeDto.apply(e))
                .collect(Collectors.toList());
    }*/
}
