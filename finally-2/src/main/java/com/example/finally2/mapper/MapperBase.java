package com.example.finally2.mapper;

import java.util.List;

public interface MapperBase <E ,D>{

    D toDTO(E e);

    List<D> listToDTO(List<E> listE);

    E toEntity(D d);

    List<E> toListEntity(List<D> d);
}
