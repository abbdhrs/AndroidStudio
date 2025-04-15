import retrofit2.Call
import retrofit2.http.GET
import com.example.firstapp.User


interface ApiService {
    @GET("backendApp/get_users.php")
    fun getUsers(): Call<List<User>>
}
