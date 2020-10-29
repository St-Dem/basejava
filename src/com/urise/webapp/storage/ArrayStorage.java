package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;

    /*
     *Удаляем все значимые части массива
     */
    public void clear() {
        storage = Arrays.copyOfRange(storage, 0, 0);
        size = 0;
    }

    /*
     * Не понимаю что мы должны менять.
     * Если uuid то нужно сравниваться по другому ключу, а ключей больше нет.
     */
    public void update(Resume resume) {
        if (resume == null || size == 0) return;
        int j = -1;
        try {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(resume.getUuid())) {
                    storage[i].setUuid(resume.getUuid());
                    j = i;
                    break;
                }
            }
            if (j == -1) System.out.println("Резюме не существует");
        } catch (Exception e) {

        }
    }

    /*
     * Добавляем значения в массив
     * Если резюме отсутствует выходим.
     * Если резюме присутствует в базе выходим.
     * Если массив резюме полон - выводится предупреждение.
     */
    public void save(Resume r) {
        if (r.getUuid().isEmpty()) return;
        if (size == 9999) {
            System.out.println("Резюме заполнено");
            return;
        }
        try {
            for (Resume a : storage) {
                if (r.getUuid().equals(a.getUuid())) {
                    System.out.println("Резюме существует");
                    return;
                }
            }

        } catch (Exception e) {

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
        if (uuid == null) return null;
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        System.out.println("Резюме не существует");
        return null;
    }

    /*
     * Удаляется объект по ключу
     * Если удалять нечего или неоткуда - ничего не делаем.
     */
    public void delete(String uuid) {
        if (uuid == null || size == 0) return;
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
        } else {
            System.out.println("Резюме не существует");
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    /*
     * Возвращается массив состоящий только из значений (не null)
     */
    public Resume[] getAll() {
        Resume[] resume = Arrays.copyOf(storage, size);

        return resume;
    }

    /*
     * Возвращается количество ненулевых элементов массива
     */
    public int size() {
        return size;
    }


}
