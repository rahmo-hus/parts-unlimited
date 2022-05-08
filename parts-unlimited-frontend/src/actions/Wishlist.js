import {WISHLIST_ADD_PRODUCT, WISHLIST_REMOVE_PRODUCT} from "./Types";

export function addToWishList(product) {
    return {
        type: WISHLIST_ADD_PRODUCT,
        payload: product
    };
}

export function removeFromWishList(product) {
    return {
        type: WISHLIST_REMOVE_PRODUCT,
        payload: product
    };
}
