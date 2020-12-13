import React, {Component} from 'react';
import { useState } from 'react';
import App from "../App";
export const AppContext = React.createContext();

function ContextApi(){
    const[appState,setAppState] =useState({
        username:'',
        password:''
    })

    return(
        <AppContext.Provider value={{appState:appState,setAppState:setAppState}}>
            <App/>
        </AppContext.Provider>
    );

}
export default ContextApi;
/*class ContextApi extends Component {
    constructor(props) {
        super(props);
        this.state={
            user:'',
            password:''
        }
    }

    render() {
        return (
            <ContextApi.Provider value={this.state}>
                {this.props.children}
            </ContextApi.Provider>
        );
    }
}
const UserConsumer = ContextApi.Consumer;
export default ContextApi;*/


