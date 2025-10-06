package com.workflex.workation_platform_backend.mapper;

public interface Mapper<D, E> {
    D toDomain(E e);

}
