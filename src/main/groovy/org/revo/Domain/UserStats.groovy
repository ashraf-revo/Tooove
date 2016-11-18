package org.revo.Domain

import java.util.concurrent.atomic.AtomicLong

/**
 * Created by ashraf on 05/11/16.
 */
class UserStats {
    private AtomicLong last = new AtomicLong(System.currentTimeMillis())

    void mark() {
        this.last.set(System.currentTimeMillis())
    }

    AtomicLong getLast() {
        return last
    }
}
