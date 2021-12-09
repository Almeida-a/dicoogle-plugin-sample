/**
 * Copyright (C) 2014  Universidade de Aveiro, DETI/IEETA, Bioinformatics Group - http://bioinformatics.ua.pt/
 *
 * This file is part of Dicoogle/dicoogle-plugin-sample.
 *
 * Dicoogle/dicoogle-plugin-sample is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Dicoogle/dicoogle-plugin-sample is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Dicoogle.  If not, see <http://www.gnu.org/licenses/>.
 */

package pt.ieeta.dicoogle.plugin.sample.query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pt.ieeta.dicoogle.plugin.sample.MemoryDICOMDB;
import pt.ua.dicoogle.sdk.QueryInterface;
import pt.ua.dicoogle.sdk.datastructs.SearchResult;
import pt.ua.dicoogle.sdk.settings.ConfigurationHolder;

import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * Example of a query provider.
 *
 * @author Luís A. Bastião Silva - <bastiao@ua.pt>
 * @author Rui Lebre - <ruilebre@ua.pt>
 */
public class SampleQueryPlugin implements QueryInterface {
    private static final Logger logger = LoggerFactory.getLogger(SampleQueryPlugin.class);
    private final MemoryDICOMDB memoryDicomDB;
    private boolean enabled;
    private ConfigurationHolder settings;

    public SampleQueryPlugin(MemoryDICOMDB memoryDicomDB) {
        this.memoryDicomDB = memoryDicomDB;
        this.enabled = true;
    }

    private SearchResult generateSearchResult() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("PatientID", UUID.randomUUID().toString());
        map.put("PatientName", UUID.randomUUID().toString());
        map.put("SOPInstanceUID", UUID.randomUUID().toString());
        map.put("SeriesInstanceUID", UUID.randomUUID().toString());
        map.put("StudyInstanceUID", UUID.randomUUID().toString());
        map.put("Modality", "CT");
        map.put("StudyDate", "20150120");
        map.put("SeriesDate", "20150120");

        // TODO check if this "///" works on linux
        SearchResult r = new SearchResult(URI.create("file:///" + UUID.randomUUID().toString()), 1, map);

        return r;
    }


    @Override
    public Iterable<SearchResult> query(String query, Object... parameters) {
        List<SearchResult> results = new ArrayList<>();
        results.add(generateSearchResult());
        results.add(generateSearchResult());
        results.add(generateSearchResult());
        results.add(generateSearchResult());
        results.add(generateSearchResult());

        return results;
    }

    @Override
    public String getName() {
        return "sample-plugin-query";
    }

    @Override
    public boolean enable() {
        this.enabled = true;
        return true;
    }

    @Override
    public boolean disable() {
        this.enabled = false;
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public ConfigurationHolder getSettings() {
        return this.settings;
    }

    @Override
    public void setSettings(ConfigurationHolder settings) {
        this.settings = settings;
    }

}
