package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10_000];
    private int size;
    private int index;

    /*
     *Удаляем все значимые части массива
     */
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /*
     * Метод меняет значение резюме по ключу uuid
     *
     */
    public void update(Resume resume) {
        if (resume == null || size == 0) {
            return;
        }
        index = getIndex(resume.getUuid());
        if (index == -1) {
            System.out.println("Резюме не существует " + resume.getUuid());
        } else storage[index] = resume;
    }

    /*
     * Добавляем значения в массив
     * Если резюме отсутствует выходим.
     * Если резюме присутствует в базе выходим.
     * Если массив резюме полон - выводится предупреждение.
     */
    public void save(Resume resume) {
        if (resume == null) {
            return;
        }
        if (size < storage.length) {
            if (getIndex(resume.getUuid()) != -1) {
                System.out.println("Резюме существует " + resume.getUuid());
            } else {
                storage[size] = resume;
                size++;
            }
        } else {
            System.out.println("Заполнен массив резюме");
        }

    }

    /*
     *Получить объект по ключу
     * Если объекта нет выводится null
     * Если ничего не введено возвращается null
     */
    public Resume get(String uuid) {
        if (uuid == null || size == 0) {
            return null;
        }
        index = getIndex(uuid);
        if (index == -1) {
            System.out.println("Резюме не существует " + uuid);
            return null;
        }
        return storage[index];
    }

    /*
     * Удаляется объект по ключу
     * Если удалять нечего или неоткуда - ничего не делаем.
     */
    public void delete(String uuid) {
        if (uuid == null || size == 0) {
            return;
        }
        index = getIndex(uuid);
        if (index == -1) System.out.println("Резюме не существует " + uuid);
        else {
            System.arraycopy(storage, index + 1, storage, index, size - 1 - index);
            storage[size - 1] = null;
            size--;
        }
    }

    /*
     * Проверка на индекс резюме по ключу.
     */
    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    /*
     * Возвращается массив состоящий только из значений (не null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    /*
     * Возвращается количество резюме
     */
    public int size() {
        return size;
    }
}