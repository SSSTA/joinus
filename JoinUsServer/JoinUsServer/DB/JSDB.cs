using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Data;
using MySql.Data.MySqlClient;


namespace JoinUsServer
{
    public class JSDB
    {
        private string connectionstr = "Server=localhost;Database=JSDB;Uid=root;Pwd=admin;";
        private MySqlConnection sqlcon;
        private MySqlDataReader reader;
        public JSDB ( )
        {
            try
            {
                sqlcon = new MySqlConnection(connectionstr);
            }
            catch
            {
                Console.WriteLine("连接数据库失败!");
            }
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="query"></param>
        /// <returns></returns>
        public string ExecuteSelectCmd ( string query )
        {
            try
            {
                string outString = "";
                MySqlCommand cmd = new MySqlCommand(query, sqlcon);
                sqlcon.Open();
                reader = cmd.ExecuteReader();
                while (reader.Read())
                {
                    for ( int i = 0 ; i < reader.FieldCount ; i++)
                    {
                        outString += reader[i];
                        outString += ConstValue.DivField;
                    }
                    outString += ConstValue.DivRow;
                }
                outString = outString.Remove(outString.LastIndexOf(ConstValue.DivRow));
                outString += ConstValue.DivEnd;       //结束符以"?end?"
                return outString;
            }
            catch
            {
                Console.WriteLine("读取失败!");
                return null;
            }
            finally
            {
                sqlcon.Close();
                reader.Close();
            }
        }

        /// <summary>
        /// 执行非查询语句
        /// </summary>
        /// <param name="query"></param>
        /// <returns>受影响的行数</returns>
        public int ExecuteNonQueryCmd ( string query )
        {
            MySqlCommand cmd = new MySqlCommand(query, sqlcon);
            sqlcon.Open();
            int rows;
            try
            {
                rows = cmd.ExecuteNonQuery();
            }
            catch
            {
                Console.WriteLine("操作失败!");
                return -1;
            }
            finally
            {
                sqlcon.Close();
            }
            return rows;
        }
    }
}
