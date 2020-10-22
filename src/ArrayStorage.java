/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    void clear() {
        int size = this.size();
       for (int i = 0; i < size; i ++){
           storage[i] = null;
            }
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }

    }

    Resume get(String uuid) {
        for (int i = 0; i < this.size(); i++) {
            if (uuid != null && storage[i].toString().equals(uuid)) {
                return storage[i];
            }
        }
        Resume resume = new Resume();
        resume.uuid = uuid;
        return resume;
    }

    void delete(String uuid) {
        for (int i = 0; i < this.size(); i++) {
            if (storage[i].toString().equals(uuid)) {
                storage[i] = null;
                break;
            }
        }
        for (int j = 0; j < this.size(); j++) {
            if (storage[j] == null && storage[j + 1] != null) {
                storage[j] = storage[j + 1];
                storage[j + 1] = null;
            }
        }

    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resume = new Resume[this.size()];
        for (int i = 0; i < this.size(); i++) {
            if (storage[i] != null) resume[i] = storage[i];
        }
        return resume;
    }

    int size() {
        int count = 0;
        for (Resume resume : storage) {
            if (resume != null) {
                count++;
            }
        }
        return count;
    }
}
