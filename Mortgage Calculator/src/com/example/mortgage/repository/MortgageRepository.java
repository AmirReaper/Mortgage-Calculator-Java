package com.example.repository;

import com.example.exception.MortgageNotFoundException;
import com.example.model.Mortgage;

import java.util.*;

/**
 * Repository for storing mortgages.
 * Implements Iterable<Mortgage> for for-each loop usage.
 */
public class MortgageRepository implements Repository<Mortgage, Integer>, Iterable<Mortgage> {
    private final List<Mortgage> mortgages = new ArrayList<>();

    @Override
    public void add(Mortgage entity) {
        mortgages.add(entity);
    }

    @Override
    public Mortgage findById(Integer id) {
        return mortgages.stream()
                .filter(m -> m.hashCode() == id)
                .findFirst()
                .orElseThrow(MortgageNotFoundException::new);
    }

    @Override
    public List<Mortgage> getAll() {
        return new ArrayList<>(mortgages);
    }

    @Override
    public void remove(Mortgage entity) {
        mortgages.remove(entity);
    }

    @Override
    public Iterator<Mortgage> iterator() {
        return mortgages.iterator();
    }
}