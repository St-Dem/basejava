package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private final Resume[] storage = new Resume[10_000];
    private int size;
    private int check;

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
        check = indexCheck(resume.getUuid());
        if (check == -1) System.out.println("Резюме не существует " + resume.getUuid());
        else storage[check] = resume;
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
        if (indexCheck(r.getUuid()) != -1) {
            System.out.println("Резюме существует " + r.getUuid());
        } else {
            storage[size] = r;
            size++;
        }
    }

    /*
     *Получить объект по ключу
     * Если объекта нет выводится null
     * Если ничего не введено возвращается null
     */
    public Resume get(String uuid) {
        if (uuidCheck(uuid == null)) return null;
        check = indexCheck(uuid);
        if (check == -1) {
            System.out.println("Резюме не существует " + uuid);
            return null;
        }
        return storage[check];
    }

    /*
     * Удаляется объект по ключу
     * Если удалять нечего или неоткуда - ничего не делаем.
     */
    public void delete(String uuid) {
        if (uuidCheck(uuid == null)) return;
        check = indexCheck(uuid);
        if (check == -1) System.out.println("Резюме не существует " + uuid);
        else {
            for (; check < size - 1; check++)
                storage[check] = storage[check + 1];
            storage[size - 1] = null;
            size--;
        }
    }

    /*
     * Проверка на пустую строку и пустое хранилище
     */
    private boolean uuidCheck(boolean b) {
        return b || size == 0;
    }

    /*
     * Проверка на индекс резюме по ключу.
     */
    private int indexCheck(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid))
                return i;
        }
        return -1;
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