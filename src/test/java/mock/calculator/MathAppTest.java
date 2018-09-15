package mock.calculator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.function.BinaryOperator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MathAppTest
{
    @Mock
    CalculatorService calculatorService;
    @InjectMocks
    MathApp mathApp = new MathApp();

    @Test
    public void testMultiply()
    {
        when(calculatorService.multiply(2.,3.)).thenReturn(6.);
        assertEquals(6.,calculatorService.multiply(2.,3.),0.1);
    }

    @Test
    public void verifyNumberOfInvocation()
    {
        //given
        when(calculatorService.multiply(2.,3.)).thenReturn(6.);
        when(calculatorService.add(4.,5.)).thenReturn(9.);
        //when
        mathApp.add(4.,5.);
        mathApp.add(4.,5.);
        mathApp.multiply(2.,3.);
        //then
        verify(calculatorService).multiply(2.,3.);
        verify(calculatorService,times(2)).add(4.,5.);
        verify(calculatorService,never()).subtract(12,3);
    }

    @Test
    public void verifyAtLeastOnceAtMost()
    {
        //given
        when(calculatorService.multiply(2.,3.)).thenReturn(6.);
        when(calculatorService.add(4.,5.)).thenReturn(9.);
        //when
        mathApp.add(4.,5.);
        mathApp.add(4.,5.);
        mathApp.multiply(2.,3.);
        //then
        verify(calculatorService,atLeast(2)).add(4.,5.);
        verify(calculatorService,atLeastOnce()).add(4.,5.);
        verify(calculatorService,atMost(1)).multiply(2.,3.);
        verify(calculatorService,atMost(1)).multiply(3.,3.);
    }

    @Test(expected = RuntimeException.class)
    public void handleWithExceptions()
    {
        //given
        when(calculatorService.divide(Matchers.anyDouble(),0.0)).thenThrow(new RuntimeException("You can't divide by 0."));
        //when
        mathApp.divide(3.,0.);
        //then exception
    }

    @Test
    public void orderVerifier()
    {
        //given
        when(calculatorService.multiply(2.,3.)).thenReturn(6.);
        when(calculatorService.add(4.,5.)).thenReturn(9.);
        InOrder inOrder = inOrder(calculatorService);
        //when
        mathApp.add(4.,5.);
        mathApp.multiply(2.,3.);
        //then
        //This will pass
        inOrder.verify(calculatorService).add(4.,5.);
        inOrder.verify(calculatorService).multiply(2.,3.);
        /*This wouldn't
        inOrder.verify(calculatorService).multiply(2.,3.);
        inOrder.verify(calculatorService).add(4.,5.);*/
    }

    @Test
    public void answerTesting()
    {
        //given
        when(calculatorService
                .multiply(Matchers.anyDouble(),Matchers.anyDouble()))
                .thenAnswer((Answer<Double>) invocationOnMock ->
                {
                    Double arg1 = invocationOnMock.getArgumentAt(0,Double.class);
                    Double arg2 = invocationOnMock.getArgumentAt(1,Double.class);
                    Double result=0.;
                    for(int i=0; i<arg2 ; i++) result+=arg1;
                    return result;
                });
        //when
        Double actual = calculatorService.multiply(3.,3.);
        //then
        assertEquals(actual,9.,0.1);
    }

    @Test
    public void spyTesting()
    {
        //given
        CalculatorService service = new CalculatorService() {
            @Override
            public double add(double input1, double input2) {
                return input1 + input2;
            }
            @Override
            public double subtract(double input1, double input2) {
                throw new UnsupportedOperationException("Method not implemented yet!");
            }

            @Override
            public double multiply(double input1, double input2) {
                throw new UnsupportedOperationException("Method not implemented yet!");
            }

            @Override
            public double divide(double input1, double input2) {
                throw new UnsupportedOperationException("Method not implemented yet!");
            }
        };
        calculatorService = spy(service);
        //when
        Double actual = calculatorService.add(3.,7.);
        //then
        assertEquals(10,actual,0.1);
    }

    @Test
    public void timeoutTest()
    {
        //given
        Runnable sleeper = () -> {
            try { Thread.sleep(100); }
            catch (InterruptedException e) { e.printStackTrace();}
            calculatorService.add(1.,2.);
        };
        when(calculatorService.add(1.,2.)).thenReturn(3.);

        //when
        new Thread(sleeper).start();
        //then
        //this will pass
        verify(calculatorService,timeout(200)).add(1.,2);
        //this wouldn't
        //verify(calculatorService,timeout(90)).add(1.,2);
    }
}
