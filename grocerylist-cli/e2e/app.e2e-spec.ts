import { GrocerylistCliPage } from './app.po';

describe('grocerylist-cli App', function() {
  let page: GrocerylistCliPage;

  beforeEach(() => {
    page = new GrocerylistCliPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
