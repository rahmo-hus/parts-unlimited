import SharedLayout from "./components/SharedLayout";
import {useState} from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import SignUp from "./components/SignUp";
import CarShop from "./components/carSpace/CarsShop";
import CarDetails from "./components/carSpace/CarDetails";
import SellACar from "./components/SellACar";
import ProductsShop from "./components/productsSpace/ProductsShop";
import SellAPart from "./components/SellAPart";
import ProductDetails from "./components/productsSpace/ProductDetails";
import Wishlist from "./components/Wishlist";
import CartFull from "./components/CartFull";
import PurchaseProduct from "./components/PurchaseProduct";
import PurchaseHistory from "./components/PurchaseHistory";
import Home from "./components/Home";

function App() {
    const [filters, setFilters] = useState({});
    return (
        <BrowserRouter>
            <SharedLayout carsFilters={setFilters}>
                <Routes>
                    <Route path='/' element={<Home/>} exact />
                    <Route path='/home' element={<Home/>} exact />
                    <Route path='/signup' element={<SignUp/>} exact/>
                    <Route path="/cars" element={<CarShop filters={filters}/>} exact/>
                    <Route path="/cars/singleproduct/:id" element={<CarDetails/>} exact/>
                    <Route path="/cars/sell" element={<SellACar/>} exact/>
                    <Route path="/products" element={<ProductsShop/>} exact/>
                    <Route path="/products/sell" element={<SellAPart/>} exact/>
                    <Route path="/products/singleproduct/:id" element={<ProductDetails/>} exact/>
                    <Route path="/wishlist" element={<Wishlist/>} exact/>
                    <Route path="/cart" element={<CartFull/>}/>
                    <Route path="/purchase" element={<PurchaseProduct/>} exact />
                    <Route path="/history" element={<PurchaseHistory/>} exact/>
                </Routes>
            </SharedLayout>
        </BrowserRouter>
    );
}

export default App;
