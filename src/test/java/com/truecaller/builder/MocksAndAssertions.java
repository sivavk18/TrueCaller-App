package com.truecaller.builder;

import com.truecaller.dto.UserDTO;
import com.truecaller.entity.User;
import io.github.benas.randombeans.EnhancedRandomBuilder;
import io.github.benas.randombeans.api.EnhancedRandom;

public class MocksAndAssertions {
    public static User BuildStudent(){
        final EnhancedRandom RANDOM = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .overrideDefaultInitialization(true).collectionSizeRange(1, 1).build();
        final User user = RANDOM.nextObject(User.class);
        return user;
    }

    public static UserDTO BuildStudentDTO(){
        final EnhancedRandom RANDOM = EnhancedRandomBuilder.aNewEnhancedRandomBuilder()
                .overrideDefaultInitialization(true).collectionSizeRange(1, 1).build();
        final UserDTO student = RANDOM.nextObject(UserDTO.class);
        student.setId(1);
        student.setEmail("sk@gmail.com");
        student.setFirstName( "sk");
        student.setLastName("p");
        return student;
    }
}
