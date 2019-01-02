package io.lerk.soultraps.sys;

/**
 * Handler interface to be used as callback.
 *
 * @param <T> type to be returned by the handler method.
 * @author Lukas Fülling (lukas@k40s.net)
 */
public interface Handler<T> {

    /**
     * Handle method
     *
     * @return the handle return value
     */
    T handle();
}
