/*
 * Copyright 2011,2012 Metamarkets Group Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.metamx.common;

import com.metamx.common.logger.Logger;

import java.util.concurrent.Callable;

/**
 */
public class Timing {
    public static <RetType> RetType timeBenchmarkWrapException(String prefix, Callable<RetType> callable, final Logger log) {
        try {
            return timeBenchmark(prefix, callable, log);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static <RetType> RetType timeBenchmark(String prefix, Callable<RetType> callable, Logger log) throws Exception {
        RetType retVal;

        long startTime = System.currentTimeMillis();
        retVal = callable.call();
        long endTime = System.currentTimeMillis();

        log.info(String.format("%s completed %,d millis.", prefix, endTime - startTime));

        return retVal;
    }
}
