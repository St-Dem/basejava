package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    /*
     *Удаляем все значимые части массива
     */
   public void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    /*
     * Добавляем значения в массив
     */
   public void save(Resume r) {
        if (r.toString() != null) {
            storage[size] = r;
            size++;
        }

    }

    /*
     *Получить объект по ключу
     * Если объекта нет выводится null
     */
   public Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid != null && storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }

        return null;
    }

    /*
     * Удаляется объект по ключу
     */
   public void delete(String uuid) {
        int j = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = null;
                j = i;
                break;
            }
        }
        if (j != -1) {
            for (; j < size - 1; j++) {
                storage[j] = storage[j + 1];
            }
            storage[size - 1] = null;
            size--;
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    /*
     * Возвращается массив состоящий только из значений (не null)
     */
   public Resume[] getAll() {
        Resume[] resume = new Resume[size];
        for (int i = 0; i < size; i++) {
            resume[i] = storage[i];
        }
        return resume;
    }

    /*
     * Подсчет элементов массива
     */
 public int size() {
        return size;
    }
}
