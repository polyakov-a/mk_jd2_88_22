package by.it_academy.jd2.mk_jd2_88_22.messenger.model.converters.api;

public interface IConverter<T, X> {

    T convertToDTO(X obj);
    X convertToEntity(T obj);
}
