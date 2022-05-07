import React, {useState} from "react";
import Tab from '@mui/material/Tab';
import {Box} from "@mui/material";
import TabList from '@mui/lab/TabList';
import TabPanel from '@mui/lab/TabPanel';
import {TabContext} from "@mui/lab";

export default function SignUp() {

    const [value, setValue] = useState(1);
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [passwordRepeat, setPasswordRepeat] = useState('');

    const registerClicked = () => {
        //handle
    }

    function loginClicked() {
        //handle login clicked
    }

    return (
        <Box sx={{width: '100%', bgcolor: 'background.paper'}}>
            <TabContext value={value}>
                <TabList onChange={(e, k) => {setValue(k)}} aria-label="disabled tabs example" centered
                >
                    <Tab label="Login" value={1}>
                    </Tab>
                    <Tab label="Register" value={2}>
                    </Tab>
                </TabList>
                <TabPanel value={2}>
                    <form className="w-100" onSubmit={registerClicked}>
                        <h3 className="text-center">Register</h3>

                        <div className="form-group">
                            <label>First name</label>
                            <input type="text" className="form-control" placeholder="John" onChange={e => setFirstName(e.target.value)}/>
                        </div>

                        <div className="form-group">
                            <label>Last name</label>
                            <input type="text" className="form-control" placeholder="Doe" onChange={e=> setLastName(e.target.value)}/>
                        </div>

                        <div className="form-group">
                            <label>Email</label>
                            <input type="email" className="form-control" placeholder="john.doe@example.com" onChange={e=>setEmail(e.target.value)}/>
                        </div>

                        <div className="form-group">
                            <label>Password</label>
                            <input type="password" className="form-control" placeholder="********" onChange={e => setPassword(e.target.value)}/>
                        </div>

                        <div className="form-group">
                            <label>Repeat password</label>
                            <input type="password" className="form-control" placeholder="********" onChange={e=> setPasswordRepeat(e.target.value)}/>
                        </div>

                        <button type="submit" className="btn btn-dark btn-lg btn-block">Register</button>
                    </form>
                </TabPanel>
                <TabPanel value={1}>
                    <form onSubmit={loginClicked}>
                        <h3 className="text-center">LOGIN</h3>
                        <div className="form-group">
                            <label>E-mail</label>
                            <input type="email" className="form-control" placeholder="user@example.com"/>
                        </div>

                        <div className="form-group">
                            <label>Password</label>
                            <input type="password" className="form-control" placeholder="********"/>
                        </div>
                        <button type="submit" className="btn btn-default btn-lg btn-block">Login</button>
                    </form>
                </TabPanel>
            </TabContext>
        </Box>
    );
}
