import Loader from 'react-loader-spinner';

const Loading=()=>{
    
    return(
        <div style={{
            width: '100%',
            height: "100",
            display: 'flex',
            justifyContent: 'center',
            alignItems: 'center',
            marginTop: '28%'
        }}>
            <Loader type="TailSpin" color="#000" height="50" width="100" />
        </div>
    )

}
export default Loading;