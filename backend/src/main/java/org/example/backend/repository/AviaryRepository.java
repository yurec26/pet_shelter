package org.example.backend.repository;

import org.example.backend.entity.Aviary;
import org.example.backend.util.IOUtil;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class AviaryRepository implements ShelterRepository<Aviary> {

    private final List<Aviary> aviaryList = new CopyOnWriteArrayList<>();

    @Override
    public Aviary getById(int id) {
        return aviaryList.stream().filter(s -> s.getId() == id).findAny().orElse(null);
    }

    @Override
    public AviaryRepository update(Aviary aviary) {
        aviaryList.set(aviaryList.indexOf(aviary), aviary);
        return this;
    }

    @Override
    public List<Aviary> getAll() {
        return aviaryList;
    }

    @Override
    public AviaryRepository loadFromRepo() throws IOException {
        IOUtil.loadFromRepoFileAviary(aviaryList, Aviary.class);
        return this;
    }

    @Override
    public void updateRepoFile() throws IOException {
        IOUtil.updateRepoFileAviary(aviaryList);
    }
}
