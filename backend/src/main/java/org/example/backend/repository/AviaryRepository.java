package org.example.backend.repository;

import org.example.backend.entity.Aviary;
import org.example.backend.util.IOUtil;

import java.io.IOException;

public class AviaryRepository extends ShelterRepository<Aviary> {

    @Override
    public void loadFromRepo() throws IOException {
        IOUtil.loadFromRepoFileAviary(entities);
    }

    @Override
    public void updateRepoFile() throws IOException {
        IOUtil.updateRepoFileAviary(entities);
    }
}
