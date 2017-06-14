package com.josalvdel1.randomusercodetest.ui.module.userlist;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import com.josalvdel1.randomusercodetest.ChildsViewActions;
import com.josalvdel1.randomusercodetest.R;
import com.josalvdel1.randomusercodetest.domain.entity.User;
import com.josalvdel1.randomusercodetest.domain.repository.UserRepository;
import com.josalvdel1.randomusercodetest.ui.module.userdetail.UserDetailActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class UserListActivityTest {

    @org.mockito.Mock
    UserRepository userRepository;

    @Rule
    public IntentsTestRule<UserListActivity> activityRule =
            new IntentsTestRule<>(UserListActivity.class, true, false);

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldBeAnLoadMoreButton() {

        activityRule.launchActivity(null);

        onView(withId(R.id.fab_load_more))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldLoadMoreUsersWhenLoadMoreButtonPressed() {
        activityRule.launchActivity(null);

        onView(withId(R.id.fab_load_more))
                .check(matches(isDisplayed()));
    }

    @Test
    public void shouldOpenUserDetailActivityOnUserImageClicked() throws Exception {

        when(userRepository.getOldUsers()).thenReturn(getSampleUsers());

        activityRule.launchActivity(null);

        onView(withId(R.id.rv_users)).
                perform(RecyclerViewActions.actionOnItemAtPosition(0,
                        ChildsViewActions.clickChildWithId(R.id.iv_user_thumbnail)));

        intended(hasComponent(UserDetailActivity.class.getCanonicalName()));
    }

    public List<User> getSampleUsers() {
        List<User> sampleUsers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            User user = new User(String.valueOf(i));
            user.setFirstName("Name");
            user.setLastName("Last Name");
            user.setEmail("user@example.com");
            user.setPhone("945-456-345");
            sampleUsers.add(user);
        }

        return sampleUsers;
    }
}