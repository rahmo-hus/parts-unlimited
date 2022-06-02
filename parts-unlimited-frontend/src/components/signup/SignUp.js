import React, {useState} from "react";
import Tab from '@mui/material/Tab';
import {Box} from "@mui/material";
import TabList from '@mui/lab/TabList';
import TabPanel from '@mui/lab/TabPanel';
import {TabContext} from "@mui/lab";
import {connect} from 'react-redux';
import {login, register} from '../../actions/Auth';

function SignUp(props) {

    const [value, setValue] = useState("1");
    const [firstName, setFirstName] = useState(null);
    const [lastName, setLastName] = useState(null);
    const [username, setUsername] = useState(null);
    const [email, setEmail] = useState(null);
    const [password, setPassword] = useState(null);
    const [passwordRepeat, setPasswordRepeat] = useState(null);
    const [submitted, setSubmitted] = useState(false);
    const [registerErrorMessage, setErrorMessage] = useState('');

    const registerClicked = e => {
        e.preventDefault();
        const {dispatch} = props;
        if (firstName && lastName && email && username && password && passwordRepeat) {
            setSubmitted(true);
            dispatch(register(firstName, lastName, username, email, password, passwordRepeat));
        }
        else{
            setErrorMessage("All fields must be not null")
        }
    }

    function loginClicked(e) {
        e.preventDefault();
        setSubmitted(true);
        const {dispatch} = props;
        if (username && password) {
            dispatch(login(username, password));
        }
    }

    return (
        <Box sx={{width: '100%', bgcolor: 'background.paper'}}>
            <TabContext value={value}>
                <TabList onChange={(e, k) => {
                    setValue(k + "")
                }} aria-label="disabled tabs example" centered
                >
                    <Tab label="Login" value="1">
                    </Tab>
                    <Tab label="Register" value="2">
                    </Tab>
                </TabList>
                <TabPanel value="2">
                    <form className="w-100" onSubmit={registerClicked}>
                        <h3 className="text-center">Register</h3>

                        <div className="form-group">
                            <label>First name</label>
                            <input type="text" className="form-control" placeholder="John"
                                   onChange={e => setFirstName(e.target.value)}/>
                        </div>

                        <div className="form-group">
                            <label>Last name</label>
                            <input type="text" className="form-control" placeholder="Doe"
                                   onChange={e => setLastName(e.target.value)}/>
                        </div>

                        <div className="form-group">
                            <label>Email</label>
                            <input type="email" className="form-control" placeholder="john.doe@example.com"
                                   onChange={e => setEmail(e.target.value)}/>
                        </div>

                        <div className="form-group">
                            <label>Username</label>
                            <input type="text" className="form-control" placeholder="johndoe"
                                   onChange={e => setUsername(e.target.value)}/>
                        </div>

                        <div className="form-group">
                            <label>Password</label>
                            <input type="password" className="form-control" placeholder="********"
                                   onChange={e => setPassword(e.target.value)}/>
                        </div>

                        <div className="form-group">
                            <label>Repeat password</label>
                            <input type="password" className="form-control" placeholder="********"
                                   onChange={e => setPasswordRepeat(e.target.value)}/>
                        </div>
                        {
                            props.registered === false && <div className="alert alert-warning m-t-lg-20" role="alert">
                                Register failed. Check you data.
                            </div>
                        }
                        {
                            registerErrorMessage === false && <div className="alert alert-warning m-t-lg-20" role="alert">
                                {registerErrorMessage}
                            </div>
                        }
                        <button type="submit" className="ht-btn ht-btn-default btn-block"
                                onClick={registerClicked}>Register
                        </button>

                    </form>
                </TabPanel>
                <TabPanel value="1">
                    <form onSubmit={loginClicked}>
                        <h3 className="text-center">LOGIN</h3>

                        <div className="form-group">
                            <label>Username</label>
                            <input type="text" className="form-control" placeholder="johndoe"
                                   onChange={e => setUsername(e.target.value)}/>
                        </div>

                        <div className="form-group">
                            <label>Password</label>
                            <input type="password" value={password} onChange={e => setPassword(e.target.value)}
                                   className="form-control" placeholder="********"/>
                        </div>
                        <button type="submit" className="ht-btn ht-btn-default btn-block">Login</button>
                        {
                            props.message && <div className="alert alert-warning m-t-lg-20" role="alert">
                                Login failed. Check your credentials.
                            </div>
                        }
                    </form>
                </TabPanel>
            </TabContext>
        </Box>
    );
}

function mapStateToProps(state) {
    const {loggingIn, message, registered} = state.authentication;
    return {
        loggingIn, message, registered
    };
}

const connectedLoginPage = connect(mapStateToProps)(SignUp);
export default connectedLoginPage;
