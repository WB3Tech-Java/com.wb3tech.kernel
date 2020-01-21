package com.wb3tech.kernel.conroller;

public interface CommandUseCase<T> extends UseCase {
    void execute(T command);
}
