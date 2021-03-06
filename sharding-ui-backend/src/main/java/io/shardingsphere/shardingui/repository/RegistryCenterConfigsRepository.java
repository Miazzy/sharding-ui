/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
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
 * </p>
 */

package io.shardingsphere.shardingui.repository;

import io.shardingsphere.shardingui.common.domain.RegistryCenterConfigs;

/**
 * Registry center configs repository interface.
 *
 * @author chenqingyang
 */
public interface RegistryCenterConfigsRepository {
    
    /**
     * Load registry center configs.
     *
     * @return registry center configs
     */
    RegistryCenterConfigs load();
    
    /**
     * Save registry center configs.
     *
     * @param registryCenterConfigs registry center configs
     */
    void save(RegistryCenterConfigs registryCenterConfigs);
}
