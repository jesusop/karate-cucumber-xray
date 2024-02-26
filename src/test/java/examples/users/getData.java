package examples.users;

import com.intuit.karate.junit5.Karate;

class getData {
    
    @Karate.Test
    Karate testUsers() {
        return Karate.run("getData").relativeTo(getClass());
    }    

}
