package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10_000];
    private int size;

    /*
     *Удаляем все значимые части массива
     */
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    /*
     * Не понимаю что мы должны менять.
     * Если uuid то нужно сравниваться по другому ключу, а ключей больше нет.
     */
    public void update(Resume resume) {
        if (uuidCheck(resume == null)) return;
        int j = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(resume.getUuid())) {
                storage[i] = resume;
                j = i;
                break;
            }
        }
        if (j == -1) System.out.println("Резюме не существует " + resume.getUuid());
    }

    /*
     * Добавляем значения в массив
     * Если резюме отсутствует выходим.
     * Если резюме присутствует в базе выходим.
     * Если массив резюме полон - выводится предупреждение.
     */
    public void save(Resume r) {
        if (r.getUuid().isEmpty()) return;
        if (size == storage.length - 1) {
            System.out.println("Резюме заполнено");
            return;
        }
        for (int i = 0; i < size; i++) {
            if (r.getUuid().equals(storage[i].getUuid())) {
                System.out.println("Резюме существует " + r.getUuid());
                return;
            }
        }
        storage[size] = r;
        size++;
    }

    /*
     *Получить объект по ключу
     * Если объекта нет выводится null
     * Если ничего не введено возвращается null
     */
    public Resume get(String uuid) {
        if (uuidCheck(uuid == null)) return null;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Резюме не существует " + uuid);
        return null;
    }

    /*
     * Удаляется объект по ключу
     * Если удалять нечего или неоткуда - ничего не делаем.
     */
    public void delete(String uuid) {
        if (uuidCheck(uuid == null)) return;
        int j = -1;
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
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
        } else {
            System.out.println("Резюме не существует " + uuid);
        }
    }

    private boolean uuidCheck(boolean b) {
        return b || size == 0;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    /*
     * Возвращается массив состоящий только из значений (не null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    /*
     * Возвращается количество ненулевых элементов массива
     */
    public int size() {
        return size;
    }
}