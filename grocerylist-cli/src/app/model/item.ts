export class Item {

	constructor(
		item: Item
	) {
		this.id = item.id;
		this.name = item.name;
		this.notes = item.notes;
	}	

    id: Number;
	name: String;
	notes: String;
    
}