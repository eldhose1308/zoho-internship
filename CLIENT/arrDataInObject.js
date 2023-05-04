/**
 * 10. Write a Javascript program to add and update the array data in the object. (arrDataInObject)
 *  a. Example Object: { list : [{name : "kumaresan"}, {name : "arun"}]
 */

let myObject = {
    list: [
        {name: "Eldhose"},
        {name: "Basil"},
        {name: "Akhil"}
    ]
}


console.log(myObject.list);
myObject.list.push({name: "Sethu"});
console.log(myObject.list);
myObject.list[1].name = "Ben";
console.log(myObject.list);