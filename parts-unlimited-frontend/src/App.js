import SharedLayout from "./components/SharedLayout";
import {useState} from "react";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import SignUp from "./components/SignUp";

function App() {
    const [filters, setFilters] = useState();
    return (
        <BrowserRouter>
            <SharedLayout carsFilters={setFilters}>
                <Routes>
                    <Route path='/signup' element={<SignUp />} exact/>
                </Routes>
            </SharedLayout>
        </BrowserRouter>
    );
}

export default App;
