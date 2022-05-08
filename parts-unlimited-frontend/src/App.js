import SharedLayout from "./components/SharedLayout";
import {useState} from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import SignUp from "./components/SignUp";
import CarShop from "./components/carSpace/CarsShop";
import CarDetails from "./components/carSpace/CarDetails";

function App() {
    const [filters, setFilters] = useState({});
    return (
        <BrowserRouter>
            <SharedLayout carsFilters={setFilters}>
                <Routes>
                    <Route path='/signup' element={<SignUp />} exact/>
                    <Route path="/cars" element={<CarShop filters={filters}/> } exact />
                    <Route path="/cars/singleproduct/:id" element={<CarDetails/>} exact/>
                </Routes>
            </SharedLayout>
        </BrowserRouter>
    );
}

export default App;
