import {BASKET_ADD, BASKET_EDIT_ITEM, BASKET_REMOVE_ITEM} from "./Types";

export function addToBasket(product, quantity) {
    return {
        type: BASKET_ADD,
        payload: {
            product,
            quantity
        }
    };
}

export function getProductsFromBasket(){
    return{}
}

export function removeFromBasket(id) {
    return {
        type: BASKET_REMOVE_ITEM,
        payload: id
    };
}

export function changeBasketItem(product, quantity) {
    return {
        type: BASKET_EDIT_ITEM,
        payload: {
            product,
            quantity
        }
    };
}
