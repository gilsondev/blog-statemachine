package in.gilsondev.blog.test;

import in.gilsondev.blog.config.ValidatorConfiguration;
import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Validator;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ValidatorConfiguration.class})
public class EntityTestCase {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    protected Validator validator;
}
