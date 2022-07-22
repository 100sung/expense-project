import { useState, useEffect, useRef, useContext } from 'react';
import axios from 'axios';
import { Expenses } from './Expenses';

export const Expenselist = () => {

    const [expense, setExp] = useState([]);
    const [name, setName] = useState('');
    const [reason, setReason] = useState('');
    const [amount, setAmount] = useState(Number);

    const reasonRef = useRef();
    const amountRef = useRef();
    useEffect(() => {//setExp(res.data)
        axios.get('http://localhost:8080/expense-project/expenses')
            .then(res => setExp(res.data))  
            
    }, []);

    const handleSubmit = async (event) => {
        try{
            event.preventDefault();
            const {data} = await axios.post(('http://localhost:8080/expense-project/expenses'),
            {
                name, 
                amount: amountRef.current.value, 
                reason: reasonRef.current.value
            });
            console.log(data);
            setExp([...expense, data]);
            setName('');
            amountRef.current.value = null;
            reasonRef.current.value = null;
        }catch(err){
            console.error(err);
        }
    }

    return(
        
        <form onSubmit={handleSubmit}>
            
      <table>
        
        <thead>
            <tr>
                <th>Name</th>
                <th>Amount</th>
                <th>Reason</th>
                {/* <th>Pending</th>
                <th>Approved</th>
                <th>Denied</th> */}
            </tr>
        </thead>
        <tbody>
            {expense.map((exp) => {
                return(
                    <Expenses exp={exp} key={exp.idexpenses} expenser={expense} setExp={setExp}/>
                )
            })} 

            <tr>
                
                    <td><input name="name" value={name} onChange={(event)=> setName(event.target.value)} placeholder='Your Name'/></td>
                    <td><input amount="amount" ref={amountRef} value={amount} onChange={(event)=> setAmount(event.target.value)}/></td>
                    <td><input reason="reason" ref={reasonRef} value={reason} onChange={(event)=> setReason(event.target.value)}/></td>
                    <button>Submit</button>
                
            </tr>
        </tbody>
      </table>
      </form>
    );
}