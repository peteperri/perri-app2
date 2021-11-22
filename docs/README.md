
# Inventory Manager

A simple inventory manager created for COP3330 by Peter Perri



## Author

- [@peteperri](https://www.github.com/peteperri)


## Acknowledgements/Dependencies

- JMetro 
- Jsoup
- Gson
- Commons-io
- JUnit


## How To Use
- To add an item to the inventory, type the item name and serial number into the text fields for each. For value, you may enter any number (dollar sign or not), or leave it blank for a value of 0. Your number will be rounded and formatted as currency. The serial number must be in the format A-XXX-XXX-XXX, where A must be a letter and X must be either a letter or number. If you enter a serial number without capitalization, it will automatically be capitalized. The serial number cannot be the same as any other item's serial number.
- To remove an item from the inventory, click on it in the table view that shows all inventory items. When it is highlighted blue, this means it is selected. After this, click the "remove" button, and it will be removed from your inventory. 
- To remove ALL inventory items, click the clear button. This will completely clear your inventory. You may want to save your inventory using the save list button before doing this.
- To edit the name, value, or serial number of any item in the inventory, double click it. A text box will pop up, and you can type the new value in this text box. When you are done typing, press the enter key to save your changes. The value you enter must be valid according to the rules stated in the "adding items" section, or else this change will not be saved.
- To sort items by their name alphabetically, their value, or their serial number, click on the table headers that say "Item", "Value", or "Serial number". You can click once or twice to sort in ascending or descending order respectively.
- To search for an item, first select if you would like to search by name or by serial number. Then, enter a search string in the search bar. If your search string corresponds to an item in the inventory, only those values will be shown once you press the search button. To go back to viewing all the items in the list, simply delete the search string and click the search button again.
- To save your inventory to a file (required for inventories to persist after the application is closed), click the "save list" button. Select where you want to save your file, and what type of file you would like to save it as. The options are .html, a Tab-Separated .txt file, or a .json file. HTML files are cool if you would like to view your inventory as a table in a web browser!
- To load an inventory from a previously saved file, simply click "import list", select a file previously created by this application, and your list will be populated automatically. Note this application only supports loading files that were created using this application. If you choose to attempt to create your own, or tamper with your file using outside software (like a text editor), it is not guaranteed that your file will still load once it has been tampered with.




