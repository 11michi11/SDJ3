using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc.RazorPages;

namespace exercise01.Pages
{
    [Authorize(Policy = "MustBeAdmin")]
    public class AdminPageModel : PageModel
    {
        
    }
}