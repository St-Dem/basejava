/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size;

    /*
     *Удаляем все значимые части массива
     */
    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
    }

    /*
     * Добавляем значения в массив
     */
    void save(Resume r) {
        if (r.toString() != null) {
            storage[size()] = r;
        }

    }

    /*
     *Получить объект по ключу
     * Если объекта нет выводится null
     */
    Resume get(String uuid) {
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
    void delete(String uuid) {
        int j = 0;
        for (int i = 0; i < size; i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = null;
                j = i;
                break;
            }
        }
        for (; j < size; j++) {
            storage[j] = storage[j + 1];
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    /*
     * Возвращается массив состоящий только из значений (не null)
     */
    Resume[] getAll() {
        size();
        Resume[] resume = new Resume[size];
        for (int i = 0; i < size; i++) {
            resume[i] = storage[i];
        }
        return resume;
    }

    /*
     * Подсчет элементов массива
     */
    int size() {

        int count = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                count++;
            }
        }
        size = count;
        return count;
    }
}
