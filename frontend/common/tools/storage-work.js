class PointStorage {
    constructor(storageKey = 'points') {
        this.storage = window.sessionStorage;
        this.storageKey = storageKey;

        const existingData = this.storage.getItem(storageKey);
        if (!existingData) {
            const initialData = {
                points: []
            };
            this.storage.setItem(storageKey, JSON.stringify(initialData));
        }
    }

    addPoint(x, y) {
        const jsonString = this.storage.getItem(this.storageKey);
        const data = JSON.parse(jsonString);

        data.points.push({
            "x": x,
            "y": y
        });

        this.storage.setItem(this.storageKey, JSON.stringify(data));
    }

    getAllPoints() {
        const jsonString = this.storage.getItem(this.storageKey);
        const data = JSON.parse(jsonString);
        return data.points;
    }

    clearPoints() {
        const emptyData = {
            points: []
        };
        this.storage.setItem(this.storageKey, JSON.stringify(emptyData));
    }
}

export const pointStorage = new PointStorage();