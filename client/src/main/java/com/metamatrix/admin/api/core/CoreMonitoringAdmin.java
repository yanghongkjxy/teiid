/*
 * JBoss, Home of Professional Open Source.
 * See the COPYRIGHT.txt file distributed with this work for information
 * regarding copyright ownership.  Some portions may be licensed
 * to Red Hat, Inc. under one or more contributor license agreements.
 * 
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301 USA.
 */

package com.metamatrix.admin.api.core;

import java.util.Collection;

import com.metamatrix.admin.api.exception.AdminException;
import com.metamatrix.admin.api.objects.AdminObject;
import com.metamatrix.admin.api.objects.Cache;
import com.metamatrix.admin.api.objects.ConnectorBinding;
import com.metamatrix.admin.api.objects.ConnectorType;
import com.metamatrix.admin.api.objects.ExtensionModule;
import com.metamatrix.admin.api.objects.QueueWorkerPool;
import com.metamatrix.admin.api.objects.Request;
import com.metamatrix.admin.api.objects.Session;
import com.metamatrix.admin.api.objects.SourceRequest;
import com.metamatrix.admin.api.objects.SystemObject;
import com.metamatrix.admin.api.objects.VDB;



/**
 * Used to access the monitorable components of the MetaMatrix system.
 * 
 * <p>As a <i>core</i> interface,
 * this administration is common to both the MetaMatrix server and MM Query.</p>
 *
 * <p>See the particular admin object in question for an example of
 * allowed identifier patterns.</p>
 *
 * @since 4.3
 */
public interface CoreMonitoringAdmin {

    /**
     * Retrieve the single System object.
     *
     * @return The single {@link SystemObject} object, for interacting with system-wide configuration
     * @throws AdminException if there's a system error.
     * @since 4.3
     */
    SystemObject getSystem() throws AdminException;

    /**
     * Get the Connector Types that correspond to the specified identifier pattern.
     *
     * @param connectorTypeIdentifier the unique identifier for for a {@link ConnectorType}
     * <ul>
     *      <li> <code>"*"</code> - for all connector types in the system
     *      <li> <code>"name*"</code> - for all the connector types that begin with given name
     *      <li> <code>"name"</code> - for the single connector type identified by name
     * </ul>
     * @return Collection of {@link ConnectorType}
     * @throws AdminException if there's a system error.
     * @since 4.3
     */
    Collection getConnectorTypes(String connectorTypeIdentifier) throws AdminException;

    /**
     * Get the VDBs that correspond to the specified identifier pattern.
     *
     * @param vdbIdentifier the unique identifier for for a {@link VDB} in the system
     * <ul>
     *      <li> <code>"*"</code> - for all VDBs in the system
     *      <li> <code>"name"</code> or <code>"name*"</code> - for all the VDBs that begin with given name
     *      <li><code>"name<{@link AdminObject#DELIMITER_CHAR}>version"</code> - for single VDB
     * </ul>
     * @return Collection of {@link VDB}s.  There could be multiple VDBs with the
     * same name in the Collection but they will differ by VDB version.
     * @throws AdminException if there's a system error.
     * @since 4.3
     */
    Collection getVDBs(String vdbIdentifier) throws AdminException;

    /**
     * Get the Connector Bindings that correspond to the specified identifier pattern.
     *
     * @param connectorBindingIdentifier the unique identifier pattern of {@link ConnectorBinding}
     * <ul>
     *      <li> <code>"*"</code> - for all connector bindings in the system
     *      <li> <code>"name*"</code> - for all connector bindings that begin with given name
     *      <li><code>"name"</code> - for single connector binding by the given name
     * </ul>
     * @return Collection of {@link ConnectorBinding}
     * @throws AdminException if there's a system error.
     * @since 4.3
     */
    Collection getConnectorBindings(String connectorBindingIdentifier) throws AdminException;

    /**
     * Get all the Connector Bindings for the given VDB identifier pattern
     * @param identifier - the unique identifier for a {@link VDB}
     * <ul>
     *      <li> <code>"*"</code> - for all Connector Bindings in the system
     *      <li> <code>"name or name*"</code> - for all the bindings in the VDBs that begin with given name
     *      <li><code>"name<{@link AdminObject#DELIMITER_CHAR}>version"</code> - for all the bindings in a given single VDB
     * </ul>
     * @return Collection of {@link ConnectorBinding}
     * @throws AdminException if there's a system error.
     * @since 4.3
     */
    Collection getConnectorBindingsInVDB(String identifier) throws AdminException;

    /**
     * Get the Extension Modules that correspond to the specified identifier pattern
     * @param extensionModuleIdentifier - the unique identifier for {@link ExtensionModule}
     * <ul>
     *      <li> <code>"*"</code> - for all extension modules in the system
     *      <li> <code>"name*"</code> - for all the extension modules in that begin with given name
     *      <li><code>"name"</code> - for a single extension module identified by given name
     * </ul>
     * @return Collection of {@link ExtensionModule}
     * @throws AdminException if there's a system error.
     * @since 4.3
     */
    Collection getExtensionModules(String extensionModuleIdentifier) throws AdminException;

    /**
     * Get the Queue Worker Pools that correspond to the specified identifier pattern.
     *
     * @param identifier - an identfier for the queues {@link QueueWorkerPool}
     * <ul>
     *      <li> <code>"*"</code> - for all Queue workers in the system
     *      <li> <code>"name*"</code> - for all the Queue workers in that begin with given name
     *      <li><code>"name"</code> - for a single queue in the system
     * </ul>
     * for example, In MM Query - "dqp" will return the Stats for MM Query Worker Pool. Also any Connector Binding
     * name will return the stats for that connector binding.
     * @return Collection of {@link QueueWorkerPool}
     * @throws AdminException if there's a system error.
     * @since 4.3
     */
    Collection getQueueWorkerPools(String identifier) throws AdminException;


    /**
     * Get the Caches that correspond to the specified identifier pattern
     * @param identifier - an identifier for the cache in {@link Cache}
     * <ul>
     *      <li> <code>"*"</code> - for all different caches in the system
     *      <li> <code>"name*"</code> - for all the caches that begin with given name
     *      <li><code>"name"</code> - for a single cache in the system
     * </ul>
     * @return Collection of {@link Cache}
     * @throws AdminException if there's a system error.
     * @since 4.3
     */
    Collection getCaches(String identifier) throws AdminException;

    /**
     * Get the Sessions that correspond to the specified identifier pattern
     * @param identifier - an unique identifier for {@link Session}
     * <ul>
     *      <li> <code>"*"</code> - for all current sessions of the system
     *      <li> <code>"number*"</code> - for all the sessions that begin with given number
     *      <li><code>"number"</code> - for a single current session in the system
     * </ul>
     * @return Collection of {@link Session}
     * @throws AdminException if there's a system error.
     * @since 4.3
     */
    Collection<Session> getSessions(String identifier) throws AdminException;

    /**
     * Get the Requests that correspond to the specified identifier pattern
     * @param identifier - An Identifier for {@link Request}
     * <ul>
     *      <li> <code>"*"</code> - for all current in process requests of the system
     *      <li> <code>"number* or number<{@link AdminObject#DELIMITER_CHAR}>*"</code> - for all the sessions
     *      that begin with given number, or all the requests for particular session etc.
     *      <li><code>"number<{@link AdminObject#DELIMITER_CHAR}>number"</code> - for a single request in the system
     * </ul>
     * @return Collection of {@link Request}
     * @throws AdminException if there's a system error.
     * @since 4.3
     */

    Collection getRequests(String identifier) throws AdminException;

    /**
     * Get the Source Request that correspond to the specified identifier pattern
     * @param identifier An Identifier for {@link Request}
     * <ul>
     *      <li> <code>"*"</code> - for all current in process requests of the system
     *      <li> <code>"number* or number<{@link AdminObject#DELIMITER_CHAR}>* or number.number.*"</code> - for all the sessions
     *      that begin with given number, or all the requests for particular session etc.
     *      <li><code>"number<{@link AdminObject#DELIMITER_CHAR}>number<{@link AdminObject#DELIMITER_CHAR}>number"</code> - for a single source request in the system
     * </ul>
     * @return Collection of {@link SourceRequest}
     * @throws AdminException if there's a system error.
     * @since 4.3
     */
    Collection getSourceRequests(String identifier) throws AdminException;
    
    
    
    /**
     * Get all of the available Configuration Properties for the specified AdminObject, and details about them.
     * @param identifier
     *            The unique identifier for for an {@link AdminObject}.
     * @param className
     *            The class name of the sub-interface of {@link AdminObject} you are setting the property for. These are all the
     *            supported class names. {@link SystemObject}, {@link Host}, {@link ProcessObject}, {@link ConnectorBinding}, 
     *            {@link ConnectorType},{@link DQP}, {@link Resource}
     *            
     *            Note that in Embedded mode only supported classes are {@link ConnectorBinding}, {@link ConnectorType}, 
     *            {@link SystemObject}
     * @return Collection of PropertyDefinition objects.
     * @throws AdminException if there's a system error.     
     * @since 4.3
     */
    Collection getPropertyDefinitions(String identifier,
                                      String className) throws AdminException;

}
