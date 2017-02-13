export class Item {

	constructor(
		item: Item
	) {
		if (item != null) {
			this.id = item.id;
			this.name = item.name;
			this.notes = item.notes;
		}
	}	

    id: Number;
	name: String;
	notes: String;
    selected: Boolean;
}