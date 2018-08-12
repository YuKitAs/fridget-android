package edu.kit.pse.fridget.client.viewmodel.common;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.support.test.filters.SmallTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


@SmallTest
public class StyledContentViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    private StyledContentViewModel styledContentViewModel;

    @Before
    public void setUp() throws Exception {
        styledContentViewModel = new StyledContentViewModel("random text");
    }

    @Test
    public void toggleBold_ReturnsCorrectHtmlContent() {
        styledContentViewModel.toggleBold();

        assertThat(styledContentViewModel.getHtmlContent()).isEqualTo("<p dir=\"ltr\"><b>random text</b></p>");

        styledContentViewModel.toggleBold();

        assertThat(styledContentViewModel.getHtmlContent()).isEqualTo("<p dir=\"ltr\">random text</p>");
    }

    @Test
    public void toggleItalic_ReturnsCorrectHtmlContent() {
        styledContentViewModel.toggleItalic();

        assertThat(styledContentViewModel.getHtmlContent()).isEqualTo("<p dir=\"ltr\"><i>random text</i></p>");

        styledContentViewModel.toggleItalic();

        assertThat(styledContentViewModel.getHtmlContent()).isEqualTo("<p dir=\"ltr\">random text</p>");
    }

    @Test
    public void toggleUnderline_ReturnsCorrectHtmlContent() {
        styledContentViewModel.toggleUnderline();

        assertThat(styledContentViewModel.getHtmlContent()).isEqualTo("<p dir=\"ltr\"><u>random text</u></p>");

        styledContentViewModel.toggleUnderline();

        assertThat(styledContentViewModel.getHtmlContent()).isEqualTo("<p dir=\"ltr\">random text</p>");
    }

    @Test
    public void toggleCombinedStyles_ReturnsCorrectHtmlContent() {
        styledContentViewModel.toggleBold();
        styledContentViewModel.toggleItalic();
        styledContentViewModel.toggleUnderline();

        assertThat(styledContentViewModel.getHtmlContent()).isEqualTo("<p dir=\"ltr\"><b><i><u>random text</u></i></b></p>");

        styledContentViewModel.toggleBold();
        styledContentViewModel.toggleItalic();
        styledContentViewModel.toggleUnderline();

        assertThat(styledContentViewModel.getHtmlContent()).isEqualTo("<p dir=\"ltr\">random text</p>");
    }

    @Test
    public void parseFromHtmlContent_ReturnsTheSameHtmlContent() {
        String html = "<p dir=\"ltr\"><b><i><u>random text</u></i></b></p>";

        styledContentViewModel.setHtmlContent(html);

        assertThat(styledContentViewModel.getHtmlContent()).isEqualTo(html);
    }
}