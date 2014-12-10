require 'HTTParty'

result = HTTParty.post( "http://challenge.code2040.org/api/register", 
	:body => { "email"=> "ifkalu@yahoo.com", "github"=> "https://github.com/ikalu/code2040Challenge.git"}.to_json, 
	:headers => { 'Content-Type' => 'application/json' } )

puts result
