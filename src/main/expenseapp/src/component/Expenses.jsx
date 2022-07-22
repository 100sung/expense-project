import axios from "axios";

export const Expenses = ({exp, setExp, expenser}) =>{

    const handleDelete = async(e) =>{
        try{
            e.preventDefault();
            await axios.delete(`http://localhost:8080/expense-project/expenses/${exp.id}`);
            setExp(expenser.filter( expens => exp.id !== expens.id));
         }catch(err){
            console.error(err);
         }
        }
    return(
        <tr>
            <td>{exp.name}</td>
            <td>{exp.amount}</td>
            <td>{exp.reason}</td>
            <td>{exp.pending}</td>
            <td>{exp.approved}</td>
            <td>{exp.denied}</td>
            <button onClick={handleDelete}>Delete</button>
        </tr>
    );
}