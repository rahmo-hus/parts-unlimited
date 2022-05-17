import SharedLayout from "./components/SharedLayout";
import {useState} from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import SignUp from "./components/SignUp";
import CarShop from "./components/carSpace/CarsShop";
import CarDetails from "./components/carSpace/CarDetails";
import SellACar from "./components/SellACar";
import ProductsShop from "./components/productsSpace/ProductsShop";

function App() {
    const [filters, setFilters] = useState({});
    return (
        <BrowserRouter>
            <SharedLayout carsFilters={setFilters}>
                <Routes>
                    <Route path='/signup' element={<SignUp />} exact/>
                    <Route path="/cars" element={<CarShop filters={filters}/> } exact />
                    <Route path="/cars/singleproduct/:id" element={<CarDetails/>} exact/>
                    <Route path="/cars/sell" element={<SellACar/>} exact />
                    <Route path="/products" element={<ProductsShop/>} exact/>
                </Routes>
            </SharedLayout>
        </BrowserRouter>
    );
}

export default App;