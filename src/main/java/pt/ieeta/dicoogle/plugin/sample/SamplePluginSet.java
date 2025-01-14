/**
 * Copyright (C) 2014  Universidade de Aveiro, DETI/IEETA, Bioinformatics Group - http://bioinformatics.ua.pt/
 * <p>
 * This file is part of Dicoogle/dicoogle-plugin-sample.
 * <p>
 * Dicoogle/dicoogle-plugin-sample is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * Dicoogle/dicoogle-plugin-sample is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with Dicoogle.  If not, see <http://www.gnu.org/licenses/>.
 */

package pt.ieeta.dicoogle.plugin.sample;

import net.xeoh.plugins.base.annotations.PluginImplementation;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ieeta.dicoogle.plugin.sample.index.SampleIndexPlugin;
import pt.ieeta.dicoogle.plugin.sample.query.SampleQueryPlugin;
import pt.ieeta.dicoogle.plugin.sample.storage.SampleStoragePlugin;
import pt.ieeta.dicoogle.plugin.sample.webservice.SampleJettyPlugin;
import pt.ua.dicoogle.sdk.*;
import pt.ua.dicoogle.sdk.settings.ConfigurationHolder;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * The main plugin set.
 *
 * This is the entry point for all plugins.
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 * @author Eduardo Pinho <eduardopinho@ua.pt>
 * @author Rui Lebre - <ruilebre@ua.pt>
 */
@PluginImplementation
public class SamplePluginSet implements PluginSet {
    private static final Logger logger = LoggerFactory.getLogger(SamplePluginSet.class);

    // We will list each of our plugins as an attribute to the plugin set
    private final SampleIndexPlugin indexer;
    private final SampleQueryPlugin query;
    private final SampleJettyPlugin jettyWeb;
    private final SampleStoragePlugin storage;

    // Additional resources may be added here.
    private final MemoryDICOMDB memoryDicomDB = new MemoryDICOMDB();
    private ConfigurationHolder settings;

    public SamplePluginSet() {
        logger.info("Initializing Sample Plugin Set");

        this.indexer = new SampleIndexPlugin(memoryDicomDB);
        this.jettyWeb = new SampleJettyPlugin();
        this.query = new SampleQueryPlugin(memoryDicomDB);
        this.storage = new SampleStoragePlugin();

        logger.info("Sample Plugin Set is ready");
    }


    @Override
    public Collection<IndexerInterface> getIndexPlugins() {
        return Arrays.asList(this.indexer);
    }

    @Override
    public Collection<QueryInterface> getQueryPlugins() {
        return Arrays.asList(this.query);
    }

    /**
     * This method is used to retrieve a name for identifying the plugin set. Keep it as a constant value.
     *
     * @return a unique name for the plugin set
     */
    @Override
    public String getName() {
        return "Sample Plugin Set";
    }

    @Override
    public Collection<ServerResource> getRestPlugins() {
        return Collections.EMPTY_LIST;
    }

    @Override
    public Collection<JettyPluginInterface> getJettyPlugins() {
        return Arrays.asList(this.jettyWeb);
    }

    @Override
    public void shutdown() {
    }

    @Override
    public Collection<StorageInterface> getStoragePlugins() {
        return Arrays.asList(this.storage);
    }

    @Override
    public ConfigurationHolder getSettings() {
        return this.settings;
    }

    @Override
    public void setSettings(ConfigurationHolder xmlSettings) {
        this.settings = xmlSettings;
    }
}